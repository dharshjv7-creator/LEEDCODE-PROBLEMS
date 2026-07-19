class FooBar {

    private int n;

    Semaphore foo = new Semaphore(1); 
    Semaphore bar = new Semaphore(0); 

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            foo.acquire();        

            printFoo.run();       

            bar.release();        
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            bar.acquire();        

            printBar.run();       // Print "bar"

            foo.release();        // Give turn back to foo
        }
    }
}
