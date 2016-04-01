package ResizeTest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.Frame;

@SuppressWarnings("serial")
public class SmoothResize extends Frame {

public static void main(String[] args) {
    Toolkit.getDefaultToolkit().setDynamicLayout(true);
    System.setProperty("sun.awt.noerasebackground", "true");
    SmoothResize srtest = new SmoothResize();
    //srtest.setIgnoreRepaint(true);
    srtest.setSize(100, 100);
    srtest.setVisible(true);
}

public SmoothResize() {
    render();
}

private Dimension old_size = new Dimension(0, 0);
private Dimension new_size = new Dimension(0, 0);

public void validate() {
    super.validate();
    new_size.width = getWidth();
    new_size.height = getHeight();
    if (old_size.equals(new_size)) {
        return;
    } else {
        render();
    }
}

public void paint(Graphics g) {
    validate();
}

public void update(Graphics g) {
    paint(g);
}

public void addNotify() {
    super.addNotify();
    createBufferStrategy(2);
}

protected synchronized void render() {
    BufferStrategy strategy = getBufferStrategy();
    if (strategy == null) {
        return;
    }
    // Render single frame
    do {
        // The following loop ensures that the contents of the drawing buffer
        // are consistent in case the underlying surface was recreated
        do {
            Graphics draw = strategy.getDrawGraphics();
            Insets i = getInsets();
            int w = (int)(((double)(getWidth() - i.left - i.right))/2+0.5);
            int h = (int)(((double)(getHeight() - i.top - i.bottom))/2+0.5);
            draw.setColor(Color.WHITE);
            draw.fillRect(i.left, i.top + h, w,h);
            draw.fillRect(i.left + w, i.top, w,h);
            draw.setColor(Color.WHITE);
            draw.fillRect(i.left, i.top, w, h);
            draw.fillRect(i.left + w, i.top + h, w,h);
            draw.dispose();

            // Repeat the rendering if the drawing buffer contents 
            // were restored
        } while (strategy.contentsRestored());

        // Display the buffer
        strategy.show();

        // Repeat the rendering if the drawing buffer was lost
    } while (strategy.contentsLost());
}

}