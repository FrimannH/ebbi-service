
export class UpdateSurveyResponse {
  surveyUpdateCount: number
  statusCode: number;
  message: String;
  rowsAffected: number

  constructor(surveyUpdateCount, statusCode, message, rowsAffected) {
    this.surveyUpdateCount = surveyUpdateCount;
    this.statusCode = statusCode;
    this.message = message;
    this.rowsAffected = rowsAffected;
  }
}
