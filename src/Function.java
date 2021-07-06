import java.util.ArrayList;


abstract class Function {
        public abstract double count(ArrayList<Double> args);
        public boolean check(ArrayList<Double> args) {
                return true;
        }
        public Function(){

        }
}

class F_Sin extends Function{
        @Override
        public double count(ArrayList<Double> args)
        {
                return Math.sin(args.get(0));
        }
}


class F_Cos extends Function
{
        @Override
        public double count(ArrayList<Double> args)
        {
                return Math.cos(args.get(0));
        }
};
class F_Tg extends Function
{
        @Override
        public double count(ArrayList<Double> args)
        {
                return Math.tan(args.get(0));
        }

        @Override
        public boolean check(ArrayList<Double> args) {
                if (Math.cos(args.get(0)) == 0) return false;
                else return true;
        }
};
class F_Ctg extends Function
{
        @Override
        public double count(ArrayList<Double> args)
        {
                return 1/Math.tan(args.get(0));
        }
        @Override
        public boolean check(ArrayList<Double> args) {
                if (Math.sin(args.get(0)) == 0) return false;
                else return true;
        }
};

class F_Abs extends Function
{
        @Override
        public double count(ArrayList<Double> args) {
                return Math.abs(args.get(0));
        }
}
class F_Arcsin extends Function
{

        @Override
        public double count(ArrayList<Double> args) {
                return Math.asin(args.get(0));
        }
        @Override
        public boolean check(ArrayList<Double> args) {
               return (-1<=args.get(0) && args.get(0) <= 1);
        }
}
class F_Arccos extends Function
{
        @Override
        public double count(ArrayList<Double> args) {
                return Math.acos(args.get(0));
        }

        @Override
        public boolean check(ArrayList<Double> args) {
                return (-1<=args.get(0) && args.get(0) <= 1);
        }
}
class F_Arctg extends Function {
        @Override
        public double count(ArrayList<Double> args) {
                return Math.atan(args.get(0));
        }
}
class F_Arcctg extends Function {
        @Override
        public double count(ArrayList<Double> args) {
                return Math.PI/2 - Math.atan(args.get(0));
        }
}
class F_Exp extends Function{
        @Override
        public double count(ArrayList<Double> args) {
                return Math.exp(args.get(0));
        }
}
class F_Ln extends Function
{
        @Override
        public double count(ArrayList<Double> args) {
                return Math.log(args.get(0));
        }

        @Override
        public boolean check(ArrayList<Double> args) {
                return args.get(0) > 0;
        }
}
class F_Log extends Function
{
        @Override
        public double count(ArrayList<Double> args) {
                return Math.log(args.get(1)) / Math.log(args.get(0));
        }
        @Override
        public boolean check(ArrayList<Double> args) {
                return (args.get(0) != 1 && args.get(0) > 0 && args.get(1) > 0);
        }
}
class F_Pow extends Function
{
        @Override
        public double count(ArrayList<Double> args) {
                return Math.pow(args.get(0), args.get(1));
        }
//        @Override
//        public boolean check(ArrayList<Double> args) {
//                if (args.get(0) > 0) return true;
//                else
//                {
//                        if (args.get(1) - trunc(args.get(1)) == 0)
//                        {
//                                if (args.get(0) != 0 || args.get(1) >= 0)
//                                {
//                                        return true;
//                                }
//                        }
//                }
//                return false;
//        }
}
class F_Mult extends Function
{
        @Override
        public double count(ArrayList<Double> args) {
                return args.get(0)*args.get(1);
        }
}
class F_Div extends Function
{
        @Override
        public double count(ArrayList<Double> args) {
                return args.get(0) / args.get(1);
        }
        @Override
        public boolean check(ArrayList<Double> args) {
                return args.get(1) != 0;
        }
}
class F_Plus extends Function{
        @Override
        public double count(ArrayList<Double> args) {
                return args.get(0) + args.get(1);
        }
}
class F_Minus extends Function{
        @Override
        public double count(ArrayList<Double> args) {
                return args.get(0) - args.get(1);
        }
}