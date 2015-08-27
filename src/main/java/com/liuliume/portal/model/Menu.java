package com.liuliume.portal.model;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Vector;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Menu implements Cloneable,Serializable{
	 private String name;
	    private String url;
	    private Boolean isDisplay=false;
	    private Boolean isPermission=false;
	    private Vector<Menu> children;
	    
	    static public final Enumeration<Menu> EMPTY_ENUMERATION
	    = new Enumeration<Menu>() {
	        public boolean hasMoreElements() { return false; }
	        public Menu nextElement() {
	            throw new NoSuchElementException("No more elements");
	        }
	    };
	    
	    public Menu(){
	    	
	    }

	    public Menu(String name, String url) {
	        this.name = name;
	        this.url = url;
	    }

	    public Menu(String name, String url, List<Menu> children) {
	    	this.name = name;
	    	this.url = url;
	    	this.children = new Vector<Menu>(children);
	    }
	    public Menu(String name, String url, Vector<Menu> children) {
	        this.name = name;
	        this.url = url;
	        this.children = children;
	    }

	    public String getName() {
	        return name;
	    }

	    public String getUrl() {
	        return url;
	    }

	    public Vector<Menu> getChildren() {
			return children;
		}

		public boolean hasChildren() {
	        return !(children == null || children.isEmpty());
	    }
	    
	    public void setName(String name) {
			this.name = name;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public Enumeration<Menu> children(){
	        if (children == null) {
	            return EMPTY_ENUMERATION;
	        } else {
	            return children.elements();
	        }
	    }

	    @Override
	    public String toString(){
	    	StringBuilder sb = new StringBuilder("\nMenu:");
	    	sb.append(name).append(":").append(url);
	    	if(children!=null && !children.isEmpty()){
	    		sb.append("  ").append("  children:").append(children);
	    	}
	    	return sb.toString();
	    }
	    

		public Object clone() throws CloneNotSupportedException {
			return super.clone();
		}

		public Enumeration<Menu> breadthFirstEnumeration() {
	    	return new BreadthFirstEnumeration(this);
	    }
	    
	    private class BreadthFirstEnumeration implements Enumeration<Menu>{
	    	protected Queue queue;

			public BreadthFirstEnumeration(Menu rootNode) {
	    		Vector v = new Vector(1);
	    		v.add(rootNode); 
	            queue = new LinkedList();
	            queue.offer(v.elements());
			}
			@Override
			public boolean hasMoreElements() {
				return (!queue.isEmpty() &&
	                    ((Enumeration)queue.peek()).hasMoreElements());
			}
	    
			public Menu nextElement() {
				Enumeration iter = (Enumeration)queue.peek();
	            Menu node = (Menu)iter.nextElement();
	            Enumeration children = node.children();

	            if (!iter.hasMoreElements()) {
	                queue.remove();
	            }
	            if (children.hasMoreElements()) {
	                queue.offer(children);
	            }
	            return node;
			}
	    }
	    

		public static void main(String[] args){
			ObjectMapper o = new ObjectMapper();
			try {
				Menu m = new Menu("test1","url2");
				String s = o.writeValueAsString(m);
				Object o3 = o.readValue(s, Menu.class);
				Menu o2  = o.readValue(new File("C:/User/Lu/v3/conf/menu.json"), Menu.class);
			} catch (JsonParseException e) {
			} catch (JsonMappingException e) {
			} catch (IOException e) {
			}
		}

		public Boolean getIsDisplay() {
			return isDisplay;
		}

		public void setIsDisplay(Boolean isDisplay) {
			this.isDisplay = isDisplay;
		}

		public Boolean getIsPermission() {
			return isPermission;
		}

		public void setIsPermission(Boolean isPermission) {
			this.isPermission = isPermission;
		}
}
