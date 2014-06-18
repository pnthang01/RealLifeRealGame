package mobile.digizone;

public final class D {
	public static final class service {
		public static final String image_host = "http://digizone.vn/zone/admincafe/images/store/";
		public static final String namespace = "http://digizone.vn/";
		public static final String host = "http://digipay.vn/digizonews/";
		public static final String wsdl = "service.asmx?WSDL";
		public static final int timeout = 15000;
	}
	public static final class method {
		public static final String get_district = "getDistrict";
		public static final String get_highlight = "getHighLight";
		public static final String get_nearby = "getNearby";
		public static final String get_random = "getRandom";
		public static final String get_toprated = "getTopRated";
		public static final String get_newbie = "getNewbie";
		public static final String get_store_by_catalog = "getStoreByCatalog";
		public static final String get_store_by_district = "getStoreByDistrict";
		public static final String get_store_by_district_and_catalog = "getStoreByDistrictAndCatalog";
		public static final String get_store_by_name = "getStoreByName";
		public static final String get_store_by_unsigned_name = "getStoreByUnsignedName";
		public static final String get_store_by_ward_and_catalog = "getStoreByWWardAndCatalog";
		public static final String get_store_by_ward = "getStoreByWard";
		public static final String get_ward = "getWard";
	}
	public static final class parameter {
		public static final String latitude = "latitude";
		public static final String longitude = "longitude";
		public static final String type = "catalog";
		public static final String district = "district";
		public static final String ward = "ward";
		public static final String name = "name";
	}
	public static final class map {
		public static final int min_time = 400;
		public static final int min_distance = 1000;
		public static final int request_code = 10;
		public static final int default_zoom = 17;
	}
	public static final class pager {
		public static final int page_margin = 12;
	}
	public static final class search {
		public static final String no_type_selected = "Chọn loại";
		public static final String no_district_selected = "Chọn quận";
		public static final String no_ward_selected = "Chọn phường";
	}
	public static final class animation {
		public static final int duration = 500;
		public static final int cover_timeout = 5;
	}
}