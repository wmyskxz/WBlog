package wmyskxz.blog;

import org.junit.Test;
import org.springframework.beans.BeanUtils;

/**
 * 测试BeanUtils工具类的测试类
 *
 * @auth:wmyskxz
 * @date:2019/02/27 - 12:58
 */
public class BeanUtilsTest {

    /**
     * 类1 - 假设是我们需要映射数据库的entity类
     * 含有：property1/property2/privateProperty 三个属性
     */
    private class Entity {
        private String property1;// 属性1
        private String property2;// 属性2
        private String privateProperty;// 私有属性,即不用于展示的属性

        public String getProperty1() {
            return property1;
        }

        public void setProperty1(String property1) {
            this.property1 = property1;
        }

        public String getProperty2() {
            return property2;
        }

        public void setProperty2(String property2) {
            this.property2 = property2;
        }

        public String getPrivateProperty() {
            return privateProperty;
        }

        public void setPrivateProperty(String privateProperty) {
            this.privateProperty = privateProperty;
        }
    }

    /**
     * 类2 - 假设是我们用于与前台交互的vo
     * 含有：property1/property2 两个属性
     */
    private class Vo {
        private String property1;// 属性1
        private String property2;// 属性2

        public String getProperty1() {
            return property1;
        }

        public void setProperty1(String property1) {
            this.property1 = property1;
        }

        public String getProperty2() {
            return property2;
        }

        public void setProperty2(String property2) {
            this.property2 = property2;
        }
    }


    @Test
    public void tester() {
        Entity entity = new Entity();
        entity.setProperty1("属性1");
        entity.setProperty2("属性2");
        entity.setPrivateProperty("私有属性");

        Vo vo = new Vo();
        BeanUtils.copyProperties(entity, vo);

        System.out.println("vo属性1的值为：" + vo.getProperty1());
        System.out.println("vo属性2的值为：" + vo.getProperty2());
    }
}
