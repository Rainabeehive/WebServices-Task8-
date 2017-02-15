package edu.cmu.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;

import edu.cmu.JSON.MessageJSON;
import edu.cmu.formbean.DepositCheckFormBean;
import edu.cmu.model.Model;
import edu.cmu.resource.Controller;
import edu.cmu.resource.DepositCheckAction;

@Path("/depositCheck")
public class DepositCheck {
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public MessageJSON login(@Context HttpServletRequest request, String jsonString) throws ServletException, JSONException {
		try {
			
			JSONObject obj = new JSONObject (jsonString);
			DepositCheckFormBean depositCheckFormBean = new DepositCheckFormBean(obj.getString("username"), obj.getString("cash"));
			Controller controller = new Controller();
			controller.init();
			Model model = controller.getModel();
			return new DepositCheckAction(depositCheckFormBean, model).perform(request);
			
		} catch (Exception e){
			MessageJSON loginJSON = new MessageJSON("The input you provided is not valid");
			return loginJSON;
		} 
	}
}
