package tw.jacky.model;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

//import com.fasterxml.jackson.annotation.JsonProperty;

//import antlr.collections.List;

public class ECPayC2c {
	    
		private String MerchantID ;
	    
	    private RQHeader RqHeader;
	    
	    private String Data;

		public String getMerchantID() {
			return MerchantID;
		}

		public void setMerchantID(String merchantID) {
			MerchantID = merchantID;
		}

		public RQHeader getRqHeader() {
			return RqHeader;
		}

		public void setRqHeader(RQHeader rqHeader) {
			RqHeader = rqHeader;
		}

		public String getData() {
			return Data;
		}

		public void setData(String data) {
			Data = data;
		}

		

		
		
		

		
}
