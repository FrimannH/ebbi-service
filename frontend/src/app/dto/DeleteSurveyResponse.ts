
export class DeleteSurveyResponse {
  statusCode: number;
  message: String;
  rowsAffected: number

  constructor(statusCode, message, rowsAffected) {
    this.statusCode = statusCode;
    this.message = message;
    this.rowsAffected = rowsAffected;
  }
}
