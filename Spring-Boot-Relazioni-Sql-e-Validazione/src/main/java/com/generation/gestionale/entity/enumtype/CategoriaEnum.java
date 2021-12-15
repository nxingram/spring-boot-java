package com.generation.gestionale.entity.enumtype;

public enum CategoriaEnum {

	hardware{
		@Override
		public String toString() {
			return "Hardware";
		}
	},
	software{
		@Override
		public String toString() {
			return "Software";
		}
	};
}
