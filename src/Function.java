import java.util.ArrayList;


abstract class Function {
        public abstract double count(ArrayList<Double> args);
        public boolean check(ArrayList<Double> args) {
                return true;
        }
        public Function(){

        }
}

class Sin extends Function{
        @Override
        public double count(ArrayList<Double> args)
        {
                return Math.sin(args.get(0));
        }
}


class Cos extends Function
{
        @Override
        public double count(ArrayList<Double> args)
        {
                return Math.cos(args.get(0));
        }
};
class Tg extends Function
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
class Ctg extends Function
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

class Abs extends Function
{
        @Override
        public double count(ArrayList<Double> args) {
                return Math.abs(args.get(0));
        }
}
class Arcsin extends Function
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
class Arccos extends Function
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
class Arctg extends Function {
        @Override
        public double count(ArrayList<Double> args) {
                return Math.atan(args.get(0));
        }
}
class Arcctg extends Function {
        @Override
        public double count(ArrayList<Double> args) {
                return Math.PI/2 - Math.atan(args.get(0));
        }
}
class Exp extends Function{
        @Override
        public double count(ArrayList<Double> args) {
                return Math.exp(args.get(0));
        }
}
class Ln extends Function
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
class Log extends Function
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
class Mult extends Function
{
        @Override
        public double count(ArrayList<Double> args) {
                return args.get(0)*args.get(1);
        }
}
class Div extends Function
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
class Plus extends Function{
        @Override
        public double count(ArrayList<Double> args) {
                return args.get(0) + args.get(1);
        }
}
class Minus extends Function{
        @Override
        public double count(ArrayList<Double> args) {
                return args.get(0) - args.get(1);
        }
}