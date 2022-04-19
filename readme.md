# upickle386

An experiment with mill tasks, which might help reproducing 
https://github.com/com-lihaoyi/upickle/issues/386

```
./mill "generator[64].writeSource" 
```

To generate a case class with a random number of fields

Then 
```
 ./mill "Upickle386.compile"
```

To see if it compiles. There is probably a better way to chain the tasks together, but I was not able to figure out how to make mill do that, and it's now late, sadly... feedback welcomed.

The other degree of freedom appears to be this compiler flgag... 

```
   def scalacOptions = Seq[String](
    "-Xmax-inlines:128", // 64 solves the problem up to some point, 128 gets stack overflow
  )
  
```
Which defaults to 32. At 128 i produce stack overflow errors when compiling larger case classes. 
