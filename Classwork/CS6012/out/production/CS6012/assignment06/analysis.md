When you are satisfied that your program is correct, write a brief analysis
document. The analysis document is 10% of your Assignment 7 grade. Ensure that
your analysis document addresses the following.

1. Explain the hashing function you used for BadHashFunctor. Be sure to discuss
   why you expected it to perform badly (i.e., result in many collisions).

For the bad hashing function, I wrote a function that determines the index only by using the first letter of the string. I expect this to perform badly because every word that starts with the same letter will result in a collision.

2. Explain the hashing function you used for MediocreHashFunctor. Be sure to
   discuss why you expected it to perform moderately (i.e., result in some
   collisions).

For the mediocre hash function, I chose to sum every character in the string to find the index. I expect this to perform better than the bad hash functions because we are no longer guaranteed a collision when strings share the first letter. However, I so still expect collisions because some words which differ in characters & size can still sum to the same number. Therefore, every string that shares a sum will collide with each other.


3. Explain the hashing function you used for GoodHashFunctor. Be sure to
   discuss why you expected it to perform well (i.e., result in few or no
   collisions).

For 

5. Design and conduct an experiment to assess the quality and efficiency of
   each of your three hash functions. Briefly explain the design of your
   experiment.  Plot the results of your experiment. Since the organization of
   your plot(s) is not specified here, the labels and titles of your plot(s), as
   well as, your interpretation of the plots is important. A recommendation for
   this experiment is to create two plots: one that shows the number of collisions
   incurred by each hash function for a variety of hash table sizes, and one that
   shows the actual running time required by various operations using each hash
   function for a variety of hash table sizes.

6. What is the cost of each of your three hash functions (in Big-O notation)?
   Note that the problem size (N) for your hash functions is the length of the
   String, and has nothing to do with the hash table itself. Did each of your hash
   functions perform as you expected (i.e., do they result in the expected number
   of collisions)?

Upload your solution (.pdf only) through Canvas.