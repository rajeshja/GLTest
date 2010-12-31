package rja.android.gl;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class GLTest extends Activity
{
	GLView glView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		glView = (GLView) findViewById(R.id.gl_view);

		glView.requestFocus();
		glView.setFocusableInTouchMode(true);
    }

	public void decrementTriangles(View view) {
		glView.getRenderer().getFigure().decrementTriangles();
		glView.requestRender();
	}

	public void incrementTriangles(View view) {
		glView.getRenderer().getFigure().incrementTriangles();
		glView.requestRender();
	}
	
}
