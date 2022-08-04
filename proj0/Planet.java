public class Planet {
    private double xxPos = 0;
    private double yyPos = 0;
    private double xxVel = 0;
    private double yyVel = 0;
    private double mass = 0;
    String imgFileName = "";
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
        }
    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet p) {
        // calculates the distance between the caller and p
        // p - caller
        double d = 0;
        double dx = 0;
        double dy = 0;
        dx = p.xxPos - this.xxPos;
        dy = p.yyPos - this.yyPos;
        d = Math.pow(dx*dx+dy*dy,0.5);
        return d;

    }
    public double calcForceExertedBy(Planet p) {
        final double G = 6.67e-11;
        double force = 0;
        force = G * this.mass * p.mass
        / this.calcDistance(p) / this.calcDistance(p);
        return force;
    }
    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double force = this.calcForceExertedBy(p);
        double d = this.calcDistance(p);
        double forcex = force * dx / d;
        return forcex;
    }
    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - this.yyPos;
        double force = this.calcForceExertedBy(p);
        double d = this.calcDistance(p);
        double forcey = force * dy / d;
        return forcey;
    }
    public double calcNetForceExertedByX(Planet[] allPlanets) {
        int i = 0;
        double netForceX = 0;
        for(i = 0; i < allPlanets.length; i++) {
            if(this.equals(allPlanets[i])) {
                continue;
            }
            netForceX += this.calcForceExertedByX(allPlanets[i]);
        }
        return netForceX;
    }
    public double calcNetForceExertedByY(Planet[] allPlanets) {
        int i = 0;
        double netForceY = 0;
        for(i = 0; i < allPlanets.length; i++) {
            if(this.equals(allPlanets[i])) {
                continue;
            }
            netForceY += this.calcForceExertedByY(allPlanets[i]);
        }
        return netForceY;
    }
    public void update (double dt, double Fx, double Fy) {
        double ax = Fx / this.mass;
        double ay = Fy / this.mass;
        this.xxPos = this.xxPos + this.xxVel * dt + ax * dt * dt;
        this.yyPos = this.yyPos + this.yyVel * dt + ay * dt * dt;
        this.xxVel += ax * dt;
        this.yyVel += ay * dt;   
    }
    public void draw () {
        StdDraw.picture(this.xxPos, this.yyPos, this.imgFileName);
    }
}
