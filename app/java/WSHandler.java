package com.eserv.client;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;


public class WSHandler extends AsyncTask<String,Void,String> {
    Context context;

    WSHandler(Context ctx) {
        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        String msg = "";
        String type = params[0];
        String login_url = "http://192.168.1.6/eserv/eservweb/ws/loginprocess.php";
        String signup_url = "http://192.168.1.6/eserv/eservweb/ws/registrationprocess.php";
        String change_url = "http:/192.168.1.6/eserv/eservweb/ws/changepassword.php";
        String reset_url = "http:/192.168.1.6/eserv/eservweb/ws/resetpassword.php";
        String response = "";
        if (type.equals("login")) {
            try {

                URL url = new URL(login_url);
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("email", params[1]);
                postDataParams.put("password", params[2]);
                Log.e("params", postDataParams.toString());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));
                writer.flush();
                writer.close();
                os.close();
                int responseCode = conn.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line = "";
                    while ((line = in.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    in.close();
                    msg = sb.toString();
                    JSONObject j = new JSONObject(msg);
                    msg = j.getString("message");


                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(type == "signup") {
            try {

                URL url = new URL(signup_url);
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("fname", params[1]);
                postDataParams.put("lname", params[2]);
                postDataParams.put("line1", params[3]);
                postDataParams.put("line2", params[4]);
                postDataParams.put("city", params[5]);
                postDataParams.put("state", params[6]);
                postDataParams.put("pincode", params[7]);
                postDataParams.put("email", params[8]);
                postDataParams.put("mobile", params[9]);
                postDataParams.put("password", params[10]);
                Log.e("params", postDataParams.toString());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));
                writer.flush();
                writer.close();
                os.close();
                int responseCode = conn.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line = "";
                    while ((line = in.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    in.close();
                    msg = sb.toString();
                    JSONObject j = new JSONObject(msg);
                    msg = j.getString("message");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(type == "changepassword") {
            try {

                URL url = new URL(change_url);
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("email", params[1]);
                postDataParams.put("currentpassword", params[2]);
                postDataParams.put("newpassword", params[3]);
                postDataParams.put("confirmpassword", params[4]);
                Log.e("params", postDataParams.toString());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));
                writer.flush();
                writer.close();
                os.close();
                int responseCode = conn.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line = "";
                    while ((line = in.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    in.close();
                    msg = sb.toString();
                    JSONObject j = new JSONObject(msg);
                    msg = j.getString("message");

                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if(type == "resetpassword") {
            try {

                URL url = new URL(reset_url);
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("email", params[1]);
                Log.e("params", postDataParams.toString());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));
                writer.flush();
                writer.close();
                os.close();
                int responseCode = conn.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line = "";
                    while ((line = in.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    in.close();
                    msg = sb.toString();
                    JSONObject j = new JSONObject(msg);
                    msg = j.getString("message");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return msg;
    }
    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while (itr.hasNext()) {
            String key = itr.next();
            Object value = params.get(key);
            if (first)
                first = false;
            else
                result.append("&");
            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
        }
        return result.toString();
    }
}