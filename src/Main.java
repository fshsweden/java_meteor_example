import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import com.google.gson.Gson;

import me.kutrumbos.DdpClient;
import me.kutrumbos.enums.DdpMessageField;

public class Main implements Observer {

	public Main() {
		DdpClient client;
		try {
			client = new DdpClient("localhost", 3000);

			// create DDP client observer
			// Observer obs = new SimpleDdpClientObserver();

			// add observer
			client.addObserver(this);
			client.connect();
			//	client.subscribe("customers", null);
			
			String s1 = new String("{\"name\":\"peter andersson\", \"phone\":\"12345678\"}"); // JSON
			
			Object[] objArray = new Object[1];
//			objArray[0] = s1;
//			client.call("createNewCustomer", objArray);

			String s2 = new String("{\"id\":\"zXjLPNYmiTxvPNCRo\", \"name\":\"peter andersson\", \"phone\":\"87654321\"}"); // JSON
			
			objArray[0] = s2;
			client.call("updateCustomer", objArray);
			
			// Map<DdpMessageField, Object> x = new HashMap<DdpMessageField, Object>();
			// x.put(DdpMessageField.msg, "insert");
			// client.send(new Map<DdpMessageField, Object>)
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception ex) {
			System.out.println("Exception:" + ex.getLocalizedMessage());
		}

	}

	public void slask(String method, Object[] params){

		Map<DdpMessageField,Object> callMsg = new HashMap<DdpMessageField,Object>();
		callMsg.put(DdpMessageField.msg, "method");
		callMsg.put(DdpMessageField.method, method);
		callMsg.put(DdpMessageField.params, params);
		
		Gson gson = new Gson();
		String json = gson.toJson(callMsg);
		
		System.out.println("JSON:" + json);
	}
			
	public static void main(String[] args) {
		new Main();
	}

	@Override
	public void update(Observable client, Object msg) {

		if (msg instanceof String) {
			System.out.println("X Received response: " + msg);
		}

	}

}
