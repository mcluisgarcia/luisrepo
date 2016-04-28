package com.apress.springrecipes.court.domain;

public class SportType {

		private int id;
		private String name;
		// Constructors, Getters and Setters
		
		public SportType(int i, String string) {
			// TODO Auto-generated constructor stub
			this.id = i;
			this.name = string;
		}
		
		public int getId() {
			return id;
		}
		
		public void setId(int id) {
			this.id = id;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}

		
}
