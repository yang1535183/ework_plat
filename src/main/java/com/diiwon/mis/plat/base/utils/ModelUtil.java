package com.diiwon.mis.plat.base.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.xml.DOMConfigurator;

import com.diiwon.mis.plat.xmgl.comm.annotation.FieldInfo;


public class ModelUtil {
	    public static Map<String, Field[]> modelFields = new HashMap<String, Field[]>();
	    public static Map<String, String> dynamicSqls = new HashMap<String, String>();  // map<clazz.getName() + ColConstant.STR_GROUP + ColEnum.DynamicSqlType.e_insert.getName(), insertSql.toString()>;
	    public static Map<String, String> modelJson = new HashMap<String, String>();

	    /**
	     * 判断实体不为空
	     *
	     * @param obj
	     * @return
	     */
	    public static Boolean isNull(Object obj) {
	        return !isNotNull(obj);
	    }

	    /**
	     * 判断实体不为空
	     *
	     * @param obj
	     * @return
	     */
	    public static Boolean isNotNull(Object obj) {
	        if (obj != null) {
	            return true;
	        } else {
	            return false;
	        }
	    }

	    /**
	     * 判断实体不为空
	     *
	     * @param obj
	     * @return
	     */
	    public static Boolean isNotNull(Object obj, String message) {
	        if (obj != null) {
	            return true;
	        } else {
	            throw new IllegalArgumentException(message);
	        }
	    }

	    /**
	     * 根据实体名称获取Class对象
	     *
	     * @param className
	     * @return
	     */
	    public static Class<?> getClassByName(String className) {
	        Class<?> clazz = null;
	        try {
	            clazz = Class.forName(className);
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        return clazz;
	    }

	    /**
	     * 执行指定类的方法
	     *
	     * @param owner
	     * @param methodName
	     * @param args
	     * @return
	     */
	    public static Object invokeMethod(Object owner, String methodName, Object[] args) {
	        Class<?> ownerClass = owner.getClass();
	        Class<?>[] argsClass = null;
	        if (args != null) {
	            argsClass = new Class<?>[args.length];
	            for (int i = 0; i < args.length; i++) {
	                if (args[i] != null) {
	                    if ("java.lang.Bollean".equals(args[i].getClass())) {
	                        argsClass[i] = boolean.class;
	                    } else {
	                        argsClass[i] = args[i].getClass();
	                    }
	                } else {
	                    argsClass[i] = Object.class;
	                }
	            }
	        }
	        Method method = null;
	        try {
	            method = ownerClass.getMethod(methodName, argsClass);
	        } catch (Exception e) {
	            System.out.println("获取类函数失败");
	        }
	        Object obj = null;
	        try {
	            obj = method.invoke(owner, args);
	        } catch (Exception e) {
	            System.out.println("执行" + ownerClass.getName() + "类中的" + method.getName() + "函数失败");
	        }
	        return obj;
	    }

	    /**
	     * 执行属性的set方法
	     *
	     * @param model
	     * @param keyName
	     * @param args
	     */
	    public static void invokeSetMethod(Object model, String keyName, Object[] args) {
	        String setMethodName = "set" + keyName.substring(0, 1).toUpperCase() + keyName.substring(1);
	        invokeMethod(model, setMethodName, args);
	    }

	    /**
	     * 执行属性的get方法
	     *
	     * @param model
	     * @param proName
	     * @return
	     */
	    public static Object getPropertyValue(Object model, String proName) {
	        String getMethodName = "get" + proName.substring(0, 1).toUpperCase() + proName.substring(1);
	        Object obj = invokeMethod(model, getMethodName, null);
	        return obj;
	    }

	    /**
	     * 得到类的属性集合
	     *
	     * @param c
	     * @param itself 是否是自身的字段
	     * @return
	     */
	    public static Field[] getClassFields(Class<?> c, boolean itself) throws Exception {
	        if (itself) {
	            if (modelFields.get(c.getName()) != null) {
	                return modelFields.get(c.getName());
	            } else {
	                Field[] fields = c.getDeclaredFields();
	                modelFields.put(c.getName(), fields);
	                return fields;
	            }
	        } else {
	            if (modelFields.get(c.getName()) != null) {
	                return modelFields.get(c.getName());
	            } else {
	                List<Field> fields = new ArrayList<Field>();
	                getAllDeclaredFields(c, fields);
	                Field[] fies = new Field[fields.size()];
	                fields.toArray(fies);
	                modelFields.put(c.getName(), fies);
	                return fies;
	            }
	        }
	    }



	    /**
	     * 得到类的主键,
	     * <p/>
	     * 需要在定义实体bean的时候加上注解
	     *
	     * @param clazz
	     * @return
	     */
	    public static List<String> getClassPkName(Class<?> clazz) throws Exception {
	        Field[] fields = getClassFields(clazz, true);
	        List<String> pkNameList =new ArrayList<String>();
	        for (Field f : fields) {
	            FieldInfo fieldInfo = f.getAnnotation(FieldInfo.class);
	            if (fieldInfo.isPK()) {
	                pkNameList.add(f.getName());
	            }
	        }
	        return pkNameList;
	    }

	    /**
	     * 从c类中取得全部字段,包括父类
	     *
	     * @param c
	     * @param fields
	     */
	    private static void getAllDeclaredFields(Class<?> c, List<Field> fields) throws Exception {
	        Field[] fies = c.getDeclaredFields();
	        Collections.addAll(fields, fies);
	        Class<?> parent = c.getSuperclass();
	        if (parent != Object.class) {//如果父类不是Object就继续递归查找
	            getAllDeclaredFields(parent, fields);
	        } else {
	            return;
	        }
	    }

	    /**
	     * 传入对象
	     *
	     * @param obj 前提obj存在set get方法
	     * @throws Exception
	     * @return返回对象存在的属性值
	     */
	    public static String printModelPropertyValue(Object obj) throws Exception {
	        StringBuilder propertyText = new StringBuilder();
	        Class userClass = obj.getClass();//加载类
	        Field[] fields = ModelUtil.getClassFields(userClass, true);//获得对象方法集合
	        Method[] methods = userClass.getMethods();
	        String fdname = null;
	        Method metd = null;
	        for (Field field : fields) {// 遍历该数组
	            fdname = field.getName();// 得到字段名，
	            for (int i = 0; i < methods.length; i++) {
	                Method method = methods[i];
	                String methodName = "get" + StringUtilsExt.toUpperCaseInitial(fdname, true);
	                if (method.getName().equals(methodName)) {
	                    metd = userClass.getMethod(methodName, null);// 根据字段名找到对应的get方法，null表示无参数
	                    Object name = metd.invoke(obj, null);
	                    // 调用该字段的get方法
	                    if (name != null) {
	                        propertyText.append("," + fdname + ":" + name.toString());
	                    }
	                }
	            }

	        }
	        return propertyText.toString();
	    }

	    public static void main(String[] args) {
	        //加载log4.xml配置文件
	        DOMConfigurator.configure("D:\\yhfund\\project\\yhsvc\\out\\artifacts\\Yhsvc_Web_exploded\\WEB-INF\\classes\\cfg-log4j.xml");
	        try {
//	            ModelUtil.getDynamicSql(LevelFundParam.class,ColEnum.DynamicSqlType.e_selectByParam);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
