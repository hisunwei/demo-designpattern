package com.isunwei.designpattern.prototype;

import java.util.Date;

/**
 * prototype原型模式
 * 
 * 介绍：
 * 原型模式是创建型模式的一种,其特点在于通过“复制”一个已经存在的实例来返回新的实例,
 * 而不是新建实例。被复制的实例就是我们所称的“原型”，这个原型是可定制的。
 * 
 * 场景：
 * 原型模式多用于创建复杂的或者耗时的实例，因为这种情况下，
 * 复制一个已经存在的实例使程序运行更高效；或者创建值相等，只是命名不一样的同类数据。
 * 
 * 资料：
 * 例子來源：http://zh.wikipedia.org/wiki/%E5%8E%9F%E5%9E%8B%E6%A8%A1%E5%BC%8F
 * UML圖：http://www.dofactory.com/Patterns/Diagrams/prototype.gif
 * 
 * 
 * @author s
 *
 */
class Cookie implements Cloneable{
	String name;
	String material;
	Date date;
	@Override
	protected Cookie clone() {
		try {
			return (Cookie)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	public String toString(){
		return "name:"+name+" material:"+material+" date:"+date;
	}
}

class SunCookie extends Cookie{
	public SunCookie(){
		this.date = new Date();
		this.material = "Sand";
		this.name = "Sun Cookie";
	}
}

public class CookiesMachine{
	private Cookie cookie ;//原型
	public CookiesMachine(Cookie cookie){
		this.cookie = cookie;
	}
	
	/**
	 * 说明： 
	 * 作为cookie 交付生产后唯一变化的就是日期了，其他部分完全是一样的
	 * 所以可以直接复制其他部分，然后打上日期
	 * 
	 */
	public Cookie produce(){
		Cookie cookie = this.cookie.clone();
		if(cookie!=null && cookie.date==null){
			cookie.date = new Date();
		}
		return cookie;
	}
	
	public static void main(String[] args) {
		Cookie cookie = new Cookie();
		
		/*第一种 部分复制，然后打上日期*/
		cookie.name = "Moon Ninght";
		cookie.material = "Flour";
		CookiesMachine machine1 = new CookiesMachine(cookie);
		Cookie cookie1 = machine1.produce();
		Cookie cookie2 = machine1.produce();
		
		/*第二种 直接复制，不打日期*/
		SunCookie sunCookie = new SunCookie();
		CookiesMachine machine2 = new CookiesMachine(sunCookie);
		Cookie cookie3 = machine2.produce();
		Cookie cookie4 = machine2.produce();

		System.out.println(cookie1);
		System.out.println(cookie2);
		System.out.println(cookie3);
		System.out.println(cookie4);
	}
}
