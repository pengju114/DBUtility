
package pj.ud;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author pengju
 */
public interface UploadListener {
    public void onUploadStart(HttpServletRequest request);
    public void onUploadComplete(HttpServletRequest request);
}
