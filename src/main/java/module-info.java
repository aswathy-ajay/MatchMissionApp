module com.example.matchmissionapp {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.media;

  opens com.example.matchmissionapp to javafx.fxml;
  exports com.example.matchmissionapp;
}