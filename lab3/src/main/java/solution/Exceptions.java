package solution;

class NotEnoughDataException extends Exception {
    public NotEnoughDataException(String message) {
        super(message);
    }
}

class TooMuchDataException extends Exception {
    public TooMuchDataException(String message) {
        super(message);
    }
}

class InvalidDataException extends Exception {
    public InvalidDataException(String message) {
        super(message);
    }
}