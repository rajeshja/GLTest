package rja.android.gl;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Describe class GLView here.
 *
 *
 * Created: Mon Dec 27 18:37:51 2010
 *
 * @author <a href="mailto:rajeshja@D-174758"></a>
 * @version 1.0
 */
public class GLView extends GLSurfaceView {

	GLRenderer renderer; 

	/**
	 * Creates a new <code>GLView</code> instance.
	 *
	 */
	public GLView(Context context) {
		super(context);
		renderer = new GLRenderer();
	}

}
