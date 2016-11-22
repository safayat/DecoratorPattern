/**
 * Created by safayat on 11/23/16.
 */

interface Shape {
    void draw();
    boolean validate();
}
class Rectangle implements Shape {
    private int x1,x2,y1,y2,x3,y3, x4,y4;

    public Rectangle(int x1, int x2, int y1, int y2, int x3, int y3, int x4, int y4) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.x4 = x4;
        this.y4 = y4;
    }

    @Override
    public void draw() {
        System.out.println("Shape: Rectangle");
    }

    @Override
    public boolean validate() {
        double cx,cy;
        double dd1,dd2,dd3,dd4;

        cx=(x1+x2+x3+x4)/4;
        cy=(y1+y2+y3+y4)/4;

        dd1=Math.sqrt(cx - x1)+Math.sqrt(cy - y1);
        dd2=Math.sqrt(cx - x2)+Math.sqrt(cy - y2);
        dd3=Math.sqrt(cx - x3)+Math.sqrt(cy - y3);
        dd4=Math.sqrt(cx - x4)+Math.sqrt(cy - y4);
        return dd1==dd2 && dd1==dd3 && dd1==dd4;
    }
}
class Line implements Shape {
    private int x1,x2,y1,y2,x3,y3;

    public Line(int x1, int y1, int x2, int y2, int x3, int y3) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    @Override
    public void draw() {
        System.out.println("Shape: Circle");
    }

    @Override
    public boolean validate() {
        return (y1 - y2) * (x1 - x3) == (y1 - y3) * (x1 - x2);
    }
}
abstract class ShapeDecorator implements Shape {
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape){
        this.decoratedShape = decoratedShape;
    }

    public void draw(){
        decoratedShape.draw();
    }
    public boolean validate(){
        return decoratedShape.validate();
    }
}

class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setRedBorder(decoratedShape);
    }

    private void setRedBorder(Shape decoratedShape){
        System.out.println("Border Color: Red");
    }

    @Override
    public boolean validate() {
        return decoratedShape.validate();
    }
}

public class DecoratorPattern {
    public static void main(String[] args) {

        Shape line = new Line(0,0,1,1,2,2);

        Shape redLine = new RedShapeDecorator(new Line(0,0,1,1,2,2));

        Shape redRectangle = new RedShapeDecorator(new Rectangle(0,0,0,4,6,0,6,4));
        System.out.println("Line with normal border");
        line.validate();
        line.draw();

        System.out.println("\nLine of red border");
        redLine.validate();
        redLine.draw();

        System.out.println("\nRectangle of red border");
        redRectangle.validate();
        redRectangle.draw();
    }

}
