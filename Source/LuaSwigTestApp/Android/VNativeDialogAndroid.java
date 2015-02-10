/*
 *
 * Confidential Information of Telekinesys Research Limited (t/a Havok). Not for disclosure or distribution without Havok's
 * prior written consent. This software contains code, techniques and know-how which is confidential and proprietary to Havok.
 * Product and Trade Secret source code contains trade secrets of Havok. Havok Software (C) Copyright 1999-2014 Telekinesys Research Limited t/a Havok. All Rights Reserved. Use of this software is subject to the terms of an end user license agreement.
 *
 */

package com.havok.Vision;

import android.app.*;
import android.content.*;
import android.text.*;
import android.util.*;
import android.view.*;
import java.util.*;

public class VNativeDialogAndroid extends AlertDialog  {
	VNativeDialogAndroid(Activity activity)
	{
		super(activity);
	}

	static native void SetDialogState(long nativePointer, int which);
	static native void SetDialogInstance(long nativePointer, VNativeDialogAndroid dialog);

	public static void CreateAndShowInstance(final long nativePointer, final Activity activity,
		final String title, final String message, final String button0, final String button1, final String button2)
	{
		activity.runOnUiThread(new Runnable()
		{
			public void run()
			{
			    // Load the native library in order to notify it when the dialog has been created.
				String libName = activity.getPackageName();
				System.loadLibrary(libName.substring(libName.lastIndexOf(".") + 1));

				VNativeDialogAndroid dialog = new VNativeDialogAndroid(activity);
				dialog.setTitle(title);
				dialog.setMessage(message);

				if(!TextUtils.isEmpty(button0))
				{
					dialog.setButton(-1, button0, new OnClickListener() {
						public void onClick(DialogInterface dialog, int which)
						{
							SetDialogState(nativePointer, 0);
						}
					});
				}

				if(!TextUtils.isEmpty(button1))
				{
					dialog.setButton(-2, button1, new OnClickListener() {
						public void onClick(DialogInterface dialog, int which)
						{
							SetDialogState(nativePointer, 1);
						}
					});
				}

				if(!TextUtils.isEmpty(button2))
				{
					dialog.setButton(-3, button2, new OnClickListener() {
						public void onClick(DialogInterface dialog, int which)
						{
							SetDialogState(nativePointer, 2);
						}
					});
				}

				dialog.setOnKeyListener(new OnKeyListener()
				{
					public boolean onKey(DialogInterface dialog, int keyCode,	KeyEvent event)
					{
						if (keyCode == KeyEvent.KEYCODE_BACK)
						{
							SetDialogState(nativePointer, -2);
							return true;
						}

						return false;
					}
				});

				dialog.setCanceledOnTouchOutside(false);
				dialog.setCancelable(false);

				dialog.show();
				SetDialogInstance(nativePointer, dialog);
			}
		});
	}

	public static void ChangeText(Activity activity, final VNativeDialogAndroid dialog, final String text)
	{
		activity.runOnUiThread(new Runnable()
		{
			public void run()
			{
				dialog.setMessage(text);
			}
		});
	}

	public static void CloseInstance(VNativeDialogAndroid dialog)
	{
		dialog.dismiss();	
	}
}

/*
 * Havok SDK - Base file, BUILD(#20140625)
 * 
 * Confidential Information of Havok.  (C) Copyright 1999-2014
 * Telekinesys Research Limited t/a Havok. All Rights Reserved. The Havok
 * Logo, and the Havok buzzsaw logo are trademarks of Havok.  Title, ownership
 * rights, and intellectual property rights in the Havok software remain in
 * Havok and/or its suppliers.
 * 
 * Use of this software for evaluation purposes is subject to and indicates
 * acceptance of the End User licence Agreement for this product. A copy of
 * the license is included with this software and is also available from salesteam@havok.com.
 * 
 */
