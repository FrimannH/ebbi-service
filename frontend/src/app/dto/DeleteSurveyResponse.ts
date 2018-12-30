
export class DeleteSurveyResponse {
  statusCode: number;
  message: String;

  constructor(statusCode, message) {
    this.statusCode = statusCode;
    this.message = message;
  }
}
