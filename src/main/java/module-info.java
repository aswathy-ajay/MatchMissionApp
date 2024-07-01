module com.example.matchmissionapp {
  requires javafx.controls;
  requires javafx.fxml;

  opens com.example.matchmissionapp to javafx.fxml;
  exports com.example.matchmissionapp;
}