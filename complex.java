package main;

import java.util.*;
import java.util.stream.*;
import java.io.*;

class Complex{
  double re, im;

  Complex(double real, double imaginary){
    this.re = real;
    this.im = imaginary;
  }

  public double norm(){
    return re * re + im * im;
  }

  public double magnitude(){
    return Math.sqrt(re * re + im * im);
  }

  public double arg(){
    return Math.atan2(im, re);
  }

  public Complex conj(){
    return new Complex(re, -im);
  }

  @Override
  public String toString() {
    return "real = " + re + ", imaginary = " + im;
  }

  @Override
  public boolean equals(Object obj) {
    if(this == obj) return true;
    if(obj == null) return false;
    /* double型との比較をしたいとき。
    if(obj.getClass() == double.class){
      return obj.equals(this.re) && this.im == 0.0;
    }*/
    //誤差が考慮されていない！！
    if(getClass() == obj.getClass()){
      Complex tmp = (Complex)obj;
      return (this.re == tmp.re) && (this.im == tmp.im);
    }
    return false;
  }

  public static Complex add(Complex a, Complex b){
    return new Complex(a.re + b.re, a.im + b.im);
  }

  public static Complex add(Complex a, double b){
    return new Complex(a.re + b, a.im);
  }

  public Complex add(Complex a){
    return new Complex(this.re + a.re, this.im + a.im);
  }

  public Complex add(double a){
    return new Complex(this.re + a, this.im);
  }

  public static Complex subtract(Complex a, Complex b){
    return add(a, b.multiply(-1));
  }

  public static Complex subtract(Complex a, double b){
    return add(a, -b);
  }

  public Complex subtract(Complex a){
    return subtract(this, a);
  }

  public Complex subtract(double a){
    return subtract(this, a);
  }

  public static Complex multiply(Complex a, Complex b){
    return new Complex(a.re * b.re - a.im * b.im, a.re * b.im + a.im * b.re);
  }

  public static Complex multiply(Complex a, double b){
    return new Complex(a.re * b, a.im * b);
  }

  public Complex multiply(Complex a){
    return new Complex(this.re * a.re - this.im * a.im, this.re * a.im + this.im * a.re);
  }

  public Complex multiply(double a){
    return new Complex(this.re * a, this.im * a);
  }

  public static Complex divide(Complex a, Complex b){
    double norm = b.re * b.re + b.im * b.im;
    return new Complex((a.re * b.re + a.im * b.im) / norm,
                      (a.re * b.im - a.im * b.re) / norm);
  }

  public static Complex divide(Complex a, double b){
    return new Complex(a.re / b, a.im / b);
  }

  public Complex divide(Complex a){
    double norm = a.re * a.re + a.im * a.im;
    return new Complex((this.re * a.re + this.im * a.im) / norm,
                      (this.re * a.im - this.im * a.re) / norm);
  }

  public Complex divide(double a){
    return new Complex(this.re / a, this.im / a);
  }
}

public class Main {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] in = br.readLine().split(" ");
    int n = Integer.parseInt(in[0]);
    int m = Integer.parseInt(in[1]);

    br.close();

    Complex c = new Complex(n, m);
    System.out.println(c.toString());
  }
}