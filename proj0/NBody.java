import java.util.ArrayList;
import java.util.List;

public class NBody {
   public static double  readRadius(String name) {
      double r = 0;
      In in = new In(name);
      int n = in.readInt();
      r = in.readDouble();
      return r; 
   }
   public static Planet[] readPlanets(String name) {
      In in = new In(name);
      // Planet[] p = new Planet[5];
      List<Planet> P = new ArrayList<Planet>();
      int n = in.readInt();
      double r = in.readDouble();
      for(int i = 0; i < n; i++) {
         double xxPos = in.readDouble();
         double yyPos = in.readDouble();
         double xxVel = in.readDouble();
         double yyVel = in.readDouble();
         double mass = in.readDouble();
         String imgFileName = in.readString();
         Planet temp = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
         P.add(temp);
      }
      int size = P.size();
      Planet[] p = new Planet[size];
      for(int i = 0; i < size; i++) {
         p[i] = P.get(i);
      }
      return p;
   }
   public static void main(String[] args) {
      double T = new Double(args[0]);
      double dt = new Double(args[1]);
      String filename = args[2];
      double uniRadius = readRadius(filename);
      double t = 0;
      Planet[] p = NBody.readPlanets(filename);
      StdDraw.enableDoubleBuffering();
      StdDraw.setScale(uniRadius * -1, uniRadius);
      StdDraw.picture(0, 0, "images/starfield.jpg");
      while(true) {
         if(t == T) {
            StdOut.printf("%d\n", p.length);
            StdOut.printf("%.2e\n", uniRadius);
            for (int i = 0; i < p.length; i++) {
                  StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  p[i].xxPos, p[i].yyPos, p[i].xxVel,
                  p[i].yyVel, p[i].mass, p[i].imgFileName);   
}

         }
         int size = p.length;
         double[] xForces = new double[size];
         double[] yForces = new double[size];
         for(int i = 0; i < size; i++) {
            xForces[i] = p[i].calcNetForceExertedByX(p);
            yForces[i] = p[i].calcNetForceExertedByY(p);
         } 
         StdDraw.picture(0, 0, "images/starfield.jpg");
         for(int i = 0; i < size; i++) {
            p[i].update(dt, xForces[i], yForces[i]);
            StdDraw.picture(p[i].xxPos, p[i].yyPos, p[i].imgFileName);
         }
         StdDraw.show();
         StdDraw.pause(10);
         t += dt;
      }

   }
}
