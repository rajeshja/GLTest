package rja.android.gl;

import android.app.Activity;
import android.os.Bundle;

public class GLTest extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

		GLView glView = new GLView(this);
        setContentView(glView);

		glView.requestFocus();
		glView.setFocusableInTouchMode(true);
    }
}
