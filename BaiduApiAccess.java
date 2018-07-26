/**
 * for http://ai.baidu.com/docs#/Auth/top
 */
package cn.colintree.aix.ApiAccess;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;

import android.os.Handler;
import cn.colintree.aix.ApiAccess.util.baidu.AuthService;

@DesignerComponent(version = BaiduApiAccess.EXTENSION_VERSION,
    description = "for http://ai.baidu.com/docs#/Auth/top",
    category = ComponentCategory.EXTENSION,
    nonVisible = true,
    iconName = "aiwebres/baidu-favicon-32.ico")
@SimpleObject(external = true)
public class BaiduApiAccess extends AndroidNonvisibleComponent {

    public static final int EXTENSION_VERSION = 3;

    private final Handler handler;

    public BaiduApiAccess(ComponentContainer container) {
        super(container.$form());
        handler = new Handler();
    }

    @SimpleFunction
    public String GetAuth(String apiKey, String securetKey) {
        return AuthService.getAuth(apiKey, securetKey);
    }

    @SimpleFunction
    public void GetAuthAsync(final String apiKey, final String securetKey) {
        new Thread(new Runnable(){
            @Override
            public void run() {
                final String auth = GetAuth(apiKey, securetKey);

                handler.post(new Runnable(){
                    @Override
                    public void run() {
                        BaiduApiAccess.this.GotAuth(auth);
                    }
                });
            }
        }).start();
    }

    @SimpleEvent
    public void GotAuth(String auth) {
        EventDispatcher.dispatchEvent(this, "GotAuth", auth);
    }

}
