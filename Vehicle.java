//EXP 2

public class Vehicle
{
    public String brandName;
    public String modelName;
    public String color;
    public double price;
    public boolean auto;
    public int noOfWheels;
    public float enginePower;
    private int noOfCylinders;
    private String mfgCode;
    public float speedLimit;


    public String getMfgCode()
    {
        return mfgCode;
    }
    public void setMfgCode(String mCode)
    {
        mfgCode = mCode;

    }
    public int getNoOfCylinders()
    {
          return noOfCylinders;
    }
    public void setNoOfCylinders(int nCylinders){
         noOfCylinders = nCylinders;
    }
    //Default Constructor
    public Vehicle()
    {
        brandName = "Honda";
        modelName = "Accord";
        price = 3400000.55;
        color = "Blue";
        mfgCode = "HON1234";
        noOfCylinders = 4;
        auto = true;
        noOfWheels = 4;
        enginePower = 3.5f;
    }
    //Parameterized constructor
    public Vehicle(String bName,String mName,String c, double p,boolean a)
    {
        brandName = bName;
        modelName = mName;
        color = c;
        price = p;
        auto = a;
    }
    public Vehicle(String c, double p,String mCode,int noOfWheels){
        color = c;
        price = p;
        mfgCode = mCode;
        this.noOfWheels = noOfWheels;

    }
    public Vehicle(Vehicle v){
        brandName = v.brandName;
        modelName = v.modelName;
        price = v.price;
        color = v.color;
        mfgCode = v.mfgCode;
        auto = v.auto;
        noOfCylinders = v.noOfCylinders;
        noOfWheels = v.noOfWheels;

    }
    public void start(int initSp)
    {
        System.out.println("I've started with an initial speed of "+initSp);

    }
    public void drive()
    {
        System.out.println("Let's go! Drive safe");
    }
    public void stop()
    {
        System.out.println("That was a nice ride");
    }
    public double calculateMileage(double dist,double fuel)
    {
        return dist/fuel;
    }
    public float changeSpeed(int curSp)
    {
    
        if (curSp <= this.speedLimit)
            System.out.println(".You are int the ideal speed range");
        else
            System.out.println("Slow down!");

        return curSp;
    }

}










































 
