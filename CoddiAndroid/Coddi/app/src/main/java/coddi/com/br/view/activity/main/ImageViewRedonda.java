package coddi.com.br.view.activity.main;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

public class ImageViewRedonda extends ImageView {

    private Path clipPath;
    private RectF rect;

    public ImageViewRedonda(Context context) {
        super(context);
    }

    public ImageViewRedonda(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageViewRedonda(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private Path getClipPatch() {
        if (this.clipPath == null) {
            this.clipPath = new Path();
        }
        return this.clipPath;
    }

    private RectF getRect() {
        if (this.rect == null) {
            this.rect = new RectF(0, 0, this.getWidth(), this.getHeight());
        }
        return this.rect;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float radius = 90.0f; // angulaçao de do circulo
        Path clipPath = getClipPatch();
        RectF rect = getRect();
        clipPath.addRoundRect(rect, radius, radius, Path.Direction.CW);
        canvas.clipPath(clipPath);
        super.onDraw(canvas);
    }

}
