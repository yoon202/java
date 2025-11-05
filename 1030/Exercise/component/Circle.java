package component;

import graphic.Shape;   // graphic 패키지의 Shape를 가져오기

public class Circle extends Shape {
    @Override
    public void draw() {
        System.out.println("Circle");
    }
}
