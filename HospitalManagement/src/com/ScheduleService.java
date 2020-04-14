package com;

import model.schedule;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;



//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


@Path("/schedule")
public class ScheduleService {
	
	
	schedule itemObj = new schedule();
	
	// Start GET method ------------------------------------------------
	
	// read schedule table details
	
	@GET
	@Path("/readItems")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	{
	return itemObj.readItems();
	}
	
	
	// display to doctors
	
	@GET
	@Path("/DisplayDoctor")
	@Produces(MediaType.TEXT_HTML)
	public String readItem()
	{
	return itemObj.DisplayDoctor();
	}
	
	
	
	// view confirmed schedule table details 
	
	@GET
	@Path("/ViewTable")
	@Produces(MediaType.TEXT_HTML)
	public String ViewTable()
	{
	return itemObj.ViewTable();
	}
	
	
	
	// display to the patients if only accepted schedules
	
	@GET
	@Path("/DisplayPatients")
	@Produces(MediaType.TEXT_HTML)
	public String DisplayPatients()
	{
	return itemObj.DisplayPatients();
	}
	
	
	
// Start POST methods ------------------------------
	
	// Insert to schedule table according to doctors requests
	
	@POST
	@Path("/insertItem")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(
	@FormParam("s") String sid,
	@FormParam("hid") String hid,
	@FormParam("hnn") String hname,
	@FormParam("dii") String did,
	@FormParam("dnn") String dname,
	@FormParam("spl") String special,
	@FormParam("da") String date,
    @FormParam("st") String start,
    @FormParam("en") String end,
    @FormParam("rm") String room,
	@FormParam("stats") String Status)
	{
	String output = itemObj.insertItem(sid, hid, hname, did,dname,special,date,start,end,room,Status);
	return output;
	}
	
	
	
	// insert only doctors confirmed schedule data to (docschedule table)
	
	@POST
	@Path("/insertConfirmSchedule")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertConfirmSchedule(
	@FormParam("s") String sid,
	//@FormParam("hid") String hid,
	@FormParam("hnn") String hname,
	//@FormParam("dii") String did,
	@FormParam("dnn") String dname,
	@FormParam("spl") String special,
	@FormParam("da") String date,
    @FormParam("st") String start,
    @FormParam("en") String end,
    @FormParam("rm") String room,
	@FormParam("stats") String Status)
	{
	String output = itemObj.insertConfirmSchedule(sid,hname,dname,special,date,start,end,room,Status);
	return output;
	}
	
	
	// beginning of the PUT method
	
	// update schedule table
	
	@PUT
	@Path("/updateItem")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData)
	{
	//Convert the input string to a JSON object
	JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
	//Read the values from the JSON object
	
	int d = itemObject.get("id").getAsInt();
	String sii = itemObject.get("s").getAsString();
	String hidd = itemObject.get("hid").getAsString();
	String hnn = itemObject.get("hnnn").getAsString();
	String didd = itemObject.get("dii").getAsString();
	String dnn = itemObject.get("dnnn").getAsString();
	String spp = itemObject.get("spl").getAsString();
	String dated = itemObject.get("da").getAsString();
	String startd = itemObject.get("st").getAsString();
	String endd = itemObject.get("en").getAsString();
	String rmd = itemObject.get("rm").getAsString();
	
	String output = itemObj.updateItem(d,sii,hidd,hnn,didd,dnn,spp,dated,startd,endd,rmd);
	
	return output;
	
	}
	
	
	
	
	// Update only status for the given schedule id 
	
	
	@PUT
	@Path("/updateTes")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateTe(String itemData)
	{
	//Convert the input string to a JSON object
	JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
	//Read the values from the JSON object
	
	String di = itemObject.get("id").getAsString();
	String s = itemObject.get("st").getAsString();
	
	String output = itemObj.updateTes(di,s);
	
	return output;
	
	}
	
	
   // beginning of the DELETE method	
	
	// Remove incorrect schedules from the schedule table
	
    @DELETE
	@Path("/deleteItem")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String itemData)
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
	//Read the value from the element <itemID>
	String id = doc.select("d").text();
	String output = itemObj.deleteItem(id);
	return output;
	}
	
	
  // Remove confirmed schedule details from docschedule table
    
	
	@DELETE
	@Path("/RemoveRecord")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String RemoveRecord(String itemData)
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
	//Read the value from the element <itemID>
	String idd = doc.select("da").text();
	String output = itemObj.RemoveRecord(idd);
	return output;
	}
	
	
	
	

}
