class FizzBuzz {
    private int n;
    private int current = 1;

    private Semaphore fizzSem = new Semaphore(0);
    private Semaphore buzzSem = new Semaphore(0);
    private Semaphore fizzBuzzSem = new Semaphore(0);
    private Semaphore numberSem = new Semaphore(1);

    public FizzBuzz(int n) {
        this.n = n;
    }

    private void next() {
        current++;

        if (current > n) {
            fizzSem.release();
            buzzSem.release();
            fizzBuzzSem.release();
            numberSem.release();
        } else if (current % 15 == 0) {
            fizzBuzzSem.release();
        } else if (current % 3 == 0) {
            fizzSem.release();
        } else if (current % 5 == 0) {
            buzzSem.release();
        } else {
            numberSem.release();
        }
    }

    public void fizz(Runnable printFizz) throws InterruptedException {
        while (true) {
            fizzSem.acquire();
            if (current > n) break;
            printFizz.run();
            next();
        }
    }

    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (true) {
            buzzSem.acquire();
            if (current > n) break;
            printBuzz.run();
            next();
        }
    }

    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (true) {
            fizzBuzzSem.acquire();
            if (current > n) break;
            printFizzBuzz.run();
            next();
        }
    }

    public void number(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            numberSem.acquire();
            if (current > n) break;
            printNumber.accept(current);
            next();
        }
    }
}
