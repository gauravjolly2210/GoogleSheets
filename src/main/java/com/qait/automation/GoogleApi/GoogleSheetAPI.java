package com.qait.automation.GoogleApi;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.FindReplaceRequest;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.mortbay.util.ajax.JSONObjectConvertor;

public class GoogleSheetAPI {
    private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String CREDENTIALS_FOLDER = "credenti"; // Directory to store user credentials.

    

    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);
    
    private static final String CLIENT_SECRET_DIR = "client_secret.json";

    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws Exception {
         InputStream in = GoogleSheetAPI.class.getResourceAsStream(CLIENT_SECRET_DIR);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(CREDENTIALS_FOLDER)))
                .setAccessType("offline")
                .build();
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }
   
    /**
     * https://docs.google.com/spreadsheets/d/1N61Do4aJhtUQJcRCR1_ybd4HbnaKbDw7ig3tLh1sJ9I/edit#gid=0
     * @throws Exception 
     */
    public static void main(String... args) throws Exception {
    	 final String spreadsheetId = "1N61Do4aJhtUQJcRCR1_ybd4HbnaKbDw7ig3tLh1sJ9I";
 	    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
 	    Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
 	            .setApplicationName(APPLICATION_NAME)
 	            .build();
       
        final String range = "A1:B";
       
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range) 
                .execute();
        
        
        List<List<Object>> values = response.getValues();
        if (values == null || values.isEmpty()) {
            System.out.println("No data found.");
        } else {
            System.out.println("Name, Major");
            for (List row : values) {
                System.out.printf("%s, %s\n", row.get(0), row.get(1));
            }
        }
//        int i=0;
//        for (List row : values) {
//            if(row.get(0).equals("gaurav")) {
//            	
//            	break;
//            }
//            i++;
//        }
//        System.out.println("A"+(i+2));
//        
//        List<List<Object>> values1=new ArrayList<List<Object>>();
//        
//        List<Object> add=new ArrayList<Object>();
//        add.add("abc");
//        values1.add(add);
//            ValueRange body = new ValueRange()
//        	      .setValues(values1);
//        	    UpdateValuesResponse result =service.spreadsheets().values()
//        	      .update(spreadsheetId, "C1", body).setValueInputOption("RAW")
//        	      .execute();
       // validating_Assertion_to_Sheet();
    }
    public static void validating_Assertion_to_Sheet(String id) throws Exception {
    	 int i=0;
    	 final String spreadsheetId = "1N61Do4aJhtUQJcRCR1_ybd4HbnaKbDw7ig3tLh1sJ9I";
 	    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
 	   final String range = "A1:B";
       
 	    Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
 	            .setApplicationName(APPLICATION_NAME)
 	            .build();
 	    
 	    
    	 ValueRange response = service.spreadsheets().values()
                 .get(spreadsheetId, range) 
                 .execute();
         List<List<Object>> values = response.getValues();
       for (List row : values) {
           if(row.get(0).equals(id)) {
           	break;
           }
           i++;
       }
       System.out.println("A"+(i+1));
       List<List<Object>> values1=new ArrayList<List<Object>>();
     
     List<Object> add=new ArrayList<Object>();
     add.add("Pass");
     values1.add(add);
         ValueRange body = new ValueRange()
     	      .setValues(values1);
     	    UpdateValuesResponse result =service.spreadsheets().values()
     	      .update(spreadsheetId, "E"+(i+1), body).setValueInputOption("RAW")
     	      .execute();
       
    }    
}