/**
 * for http://open.youtu.qq.com/#/develop/tool-authentication
 */
package cn.colintree.aix.ApiAccess;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.util.ErrorMessages;

import cn.colintree.aix.ApiAccess.util.youtu.YoutuSign;

@DesignerComponent(version = BaiduApiAccess.VERSION,
    description = "for http://open.youtu.qq.com/#/develop/tool-authentication",
    category = ComponentCategory.EXTENSION,
    nonVisible = true,
    iconName = "aiwebres/tencent-favicon.ico")
@SimpleObject(external = true)
public class YoutuApiAccess extends AndroidNonvisibleComponent {

    public static final int VERSION = 1;

    private final Form form;

    public YoutuApiAccess(ComponentContainer container) {
        super(container.$form());
        form = container.$form();
    }

    @SimpleFunction
    public String GetAuth(String appId, String secret_id, String secret_key) {
        long now = System.currentTimeMillis() / 1000;
        StringBuffer mySign = new StringBuffer();
        int status = YoutuSign.appSign(appId, secret_id, secret_key, now+3600, null, mySign);
        switch (status) {
            case 0:
                return mySign.toString();
            default:
                form.dispatchErrorOccurredEvent(this, "GetAuth", ErrorMessages.ERROR_EXTENSION_ERROR, 0, "YoutuApi", "fail generating("
                + (status==-1
                    ? "empty secret_id or secret_key"
                    : (status==-2 ? "userid too long (>64)" : "unknown"))
                +")");
                return "";
        }
    }

}
