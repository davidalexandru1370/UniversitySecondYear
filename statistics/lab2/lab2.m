n = input("number of trials\n");
p = input("probability\n");

x = 0:1:n;

disp(x);

y = binopdf(x,n,p);

plot(x,y,"*");
#keep both graphs in the same window
hold on;
xx = 0:0.01:n;
yy=binocdf(xx,n,p);
plot(xx,yy);

#as long as you are u working with discrete probabilities loads, the PDF
#must be a graph of points and a CDF must be a stairway(scari) function
#use comments in practical exam to express thinking process
