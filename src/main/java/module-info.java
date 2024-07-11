module com.example.matchmissionapp {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.media;
  requires java.logging;

  opens com.example.matchmissionapp to javafx.fxml;
  exports com.example.matchmissionapp;
}