//
//  LocalyticsPlugin.java
//
//  Copyright 2014 Localytics. All rights reserved.
//

package com.localytics.phonegap;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

import com.localytics.android.*;

/**
 * This class echoes a string called from JavaScript.
 */
public class LocalyticsPlugin extends CordovaPlugin {

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("init")) {
            callbackContext.success();
            return true;
        }
        else if (action.equals("upload")) {
            Localytics.upload();
            callbackContext.success();
            return true;
        }
        else if (action.equals("upload")) {
            Localytics.upload();
            callbackContext.success();
            return true;
        }
        else if (action.equals("tagEvent")) {
            if (args.length() == 3) {
                String name = args.getString(0);
                if (name != null && name.length() > 0) {
                    JSONObject attributes = null;
                    if (!args.isNull(1)) {
                        attributes = args.getJSONObject(1);
                    }
                    HashMap<String, String> a = null;
                    if (attributes != null && attributes.length() > 0) {
                        a = new HashMap<String, String>();
                        Iterator<?> keys = attributes.keys();
                        while (keys.hasNext()) {
                            String key = (String)keys.next();
                            String value = attributes.getString(key);
                            a.put(key, value);
                        }
                    }
                    int customerValueIncrease = args.getInt(2);
                    Localytics.tagEvent(name, a, customerValueIncrease);
                    callbackContext.success();
                } else {
                    callbackContext.error("Expected non-empty name argument.");
                }
            } else {
                callbackContext.error("Expected three arguments.");
            }
            return true;
        }
        else if (action.equals("tagScreen")) {
            String name = args.getString(0);
            if (name != null && name.length() > 0) {
                Localytics.tagScreen(name);
                callbackContext.success();
            } else {
                callbackContext.error("Expected non-empty name argument.");
            }
            return true;
        }
        else if (action.equals("setCustomDimension")) {
            if (args.length() == 2) {
                int index = args.getInt(0);
                String value = null;
                if (!args.isNull(1)) {
                    value = args.getString(1);
                }
                Localytics.setCustomDimension(index, value);
                callbackContext.success();
            } else {
                callbackContext.error("Expected two arguments.");
            }
            return true;
        }
        else if (action.equals("setProfileValue")) {
            if (args.length() == 2) {
                String name = args.getString(0);
                if (name != null && name.length() > 0) {
                    String value = null;
                    if (!args.isNull(1)) {
                        value = args.getString(1);
                    }
                    Localytics.setIdentifier(name, value);
                    callbackContext.success();
                } else {
                    callbackContext.error("Expected non-empty name argument.");
                }
            } else {
                callbackContext.error("Expected two arguments.");
            }
            return true;
        }
        else if (action.equals("setCustomerId")) {
            String id = null;
            if (!args.isNull(0)) {
                id = args.getString(0);
            }
            Localytics.setCustomerId(id);
            callbackContext.success();
            return true;
        }
        else if (action.equals("setCustomerName")) {
            String name = null;
            if (!args.isNull(0)) {
                name = args.getString(0);
            }
            Localytics.setCustomerFullName(name);
            callbackContext.success();
            return true;
        }
        else if (action.equals("setCustomerEmail")) {
            String email = null;
            if (!args.isNull(0)) {
                email = args.getString(0);
            }
            Localytics.setCustomerEmail(email);
            callbackContext.success();
            return true;
        }
        else if (action.equals("setLoggingEnabled")) {
            boolean enabled = args.getBoolean(0);
            Localytics.setLoggingEnabled(enabled);
            callbackContext.success();
            return true;
        }
        else if (action.equals("setAdvertisingIdentifierEnabled")) {
            return true;
        }

        return false;
    }
}
