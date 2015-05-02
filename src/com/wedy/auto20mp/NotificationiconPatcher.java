package com.wedy.auto20mp;

import android.content.res.XModuleResources;
import android.os.Build;
import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;

public class NotificationiconPatcher implements IXposedHookZygoteInit, IXposedHookInitPackageResources {
    private static XSharedPreferences preference = null;
    private static String MODULE_PATH = null;
/*    private Context mContext;*/

    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {
        preference = new XSharedPreferences(NotificationiconPatcher.class.getPackage().getName());
        MODULE_PATH = startupParam.modulePath;
    }

    @Override
    public void handleInitPackageResources(InitPackageResourcesParam resparam) throws Throwable {
        if (!resparam.packageName.equals("com.sonyericsson.android.camera")) {
            return;
        }

        XModuleResources modRes = XModuleResources.createInstance(MODULE_PATH, resparam.res);

        boolean isR2015 = preference.getBoolean("key_2015", false);
        if(isR2015 && Build.VERSION.SDK_INT <= 18) {
                resparam.res.setReplacement("com.sonyericsson.android.camera", "bool", "use_max_still_capture_resolution_as_default_regardless_of_display_aspect", true);
                XposedBridge.log("20MP: detected JB");
            } else if(isR2015) {
                resparam.res.setReplacement("com.sonyericsson.android.camera", "array", "ux_recommended_resolution_array_main_superiorauto_20m", modRes.fwd(R.array.A2015));
                resparam.res.setReplacement("com.sonyericsson.android.camera", "string", "cam_strings_captureframeshape_4_3_txt", modRes.fwd(R.string.cam_strings_captureframeshape_4_3_txt));
                resparam.res.setReplacement("com.sonyericsson.android.camera", "string", "cam_strings_captureframeshape_16_9_txt", modRes.fwd(R.string.cam_strings_captureframeshape_16_9_txt));
                XposedBridge.log("20+15MP: detected KK");
            }

            boolean isR208 = preference.getBoolean("key_208", false);
        if(isR208 && Build.VERSION.SDK_INT <= 18) {
                    resparam.res.setReplacement("com.sonyericsson.android.camera", "bool", "use_max_still_capture_resolution_as_default_regardless_of_display_aspect", true);
                    XposedBridge.log("20MP: detected JB");
                } else if(isR208) {
                resparam.res.setReplacement("com.sonyericsson.android.camera", "array", "ux_recommended_resolution_array_main_superiorauto_20m", modRes.fwd(R.array.A208));
                resparam.res.setReplacement("com.sonyericsson.android.camera", "string", "cam_strings_captureframeshape_4_3_txt", modRes.fwd(R.string.cam_strings_captureframeshape_4_3_txt));
                resparam.res.setReplacement("com.sonyericsson.android.camera", "string", "cam_strings_captureframeshape_16_9_txt", modRes.fwd(R.string.A8mpw));
                XposedBridge.log("20+8MP: detected KK");
            }

            boolean isR158 = preference.getBoolean("key_158", false);
            if(isR158 && Build.VERSION.SDK_INT <= 18) {
                    resparam.res.setReplacement("com.sonyericsson.android.camera", "bool", "use_max_still_capture_resolution_as_default_regardless_of_display_aspect", true);
                    XposedBridge.log("20MP: detected JB");
                } else if(isR158) {
                resparam.res.setReplacement("com.sonyericsson.android.camera", "array", "ux_recommended_resolution_array_main_superiorauto_20m", modRes.fwd(R.array.A158));
                resparam.res.setReplacement("com.sonyericsson.android.camera", "string", "cam_strings_captureframeshape_4_3_txt", modRes.fwd(R.string.A8mp));
                resparam.res.setReplacement("com.sonyericsson.android.camera", "string", "cam_strings_captureframeshape_16_9_txt", modRes.fwd(R.string.cam_strings_captureframeshape_16_9_txt));
                XposedBridge.log("15+8MP: detected KK");
            }

	boolean isR139 = preference.getBoolean("key_139", false);
            if(isR158 && Build.VERSION.SDK_INT <= 18) {
                    resparam.res.setReplacement("com.sonyericsson.android.camera", "bool", "use_max_still_capture_resolution_as_default_regardless_of_display_aspect", true);
                    XposedBridge.log("20MP: detected JB");
                } else if(isR139) {
                resparam.res.setReplacement("com.sonyericsson.android.camera", "array", "ux_recommended_resolution_array_main_superiorauto_13m", modRes.fwd(R.array.A139));
                resparam.res.setReplacement("com.sonyericsson.android.camera", "string", "cam_strings_captureframeshape_4_3_txt", modRes.fwd(R.string.A13mp));
                resparam.res.setReplacement("com.sonyericsson.android.camera", "string", "cam_strings_captureframeshape_16_9_txt", modRes.fwd(R.string.A9mp));
                XposedBridge.log("13+9MP: detected KK");
            }

            /*	boolean isLunicon = preference.getBoolean("key_lunicon", false);
            	if(isLunicon){
            	PackageManager p = mContext.getPackageManager();
ComponentName componentName = new ComponentName("com.wedy.auto20mp","com.wedy.auto20mp.SettingActivity");
p.setComponentEnabledSetting(componentName, 
    PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);

        }*/
        
}
    }