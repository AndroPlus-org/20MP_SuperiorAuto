package com.wedy.auto20mp;

import android.content.res.XModuleResources;
import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookZygoteInit;
import android.os.Build;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;

public class NotificationiconPatcher implements IXposedHookZygoteInit, IXposedHookInitPackageResources{

	private static final String PACKAGE_ESF = "com.sonyericsson.android.camera";
	private static String modulePath = null;

	@Override
	public void initZygote(StartupParam startupParam) throws Throwable {
		modulePath = startupParam.modulePath;
	}

	@Override
	public void handleInitPackageResources(InitPackageResourcesParam resparam) throws Throwable {
		if(!resparam.packageName.equals(PACKAGE_ESF)){
			return;
		}

		XModuleResources modRes = XModuleResources.createInstance(modulePath, resparam.res);
		if(Build.VERSION.SDK_INT <= 18){
		resparam.res.setReplacement(PACKAGE_ESF, "bool", "use_max_still_capture_resolution_as_default_regardless_of_display_aspect", true);
	XposedBridge.log("20MP: detected JB");
		}
		resparam.res.setReplacement(PACKAGE_ESF, "array", "ux_recommended_resolution_array_main_superiorauto_20m", modRes.fwd(R.array.ux_recommended_resolution_array_main_superiorauto_20m));
		XposedBridge.log("20MP: detected KK");
	}

}
