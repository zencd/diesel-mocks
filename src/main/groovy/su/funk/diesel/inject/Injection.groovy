package su.funk.diesel.inject

class Injection {

    private static class Instance {
        private final Map<String,Object> beanByName = [:]
        void defineBean(Object bean) {
            defineBean(bean.class.name, bean)
        }
        void defineBean(String name, Object bean) {
            assert name
            assert bean
            synchronized (beanByName) {
                beanByName[name] = bean
            }
        }
        def <T> T getBean(Class<T> clazz) {
            synchronized (beanByName) {
                def bean = beanByName[clazz.name]
                assert bean : "Cannot find bean by class ${clazz}"
                assert clazz.isInstance(bean)
                return (T)bean
            }
        }
        void clear() {
            synchronized (beanByName) {
                beanByName.clear()
            }
        }
    }

    static Instance INSTANCE = new Instance()

    static <T> T getBean(Class<T> clazz) {
        return INSTANCE.getBean(clazz)
    }
}
