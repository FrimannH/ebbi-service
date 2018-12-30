
export class UpdateSurveyResponse {
  surveyUpdateCount: number
  statusCode: number;
  message: String;

  constructor(surveyUpdateCount, statusCode, message) {
    this.surveyUpdateCount = surveyUpdateCount;
    this.statusCode = statusCode;
    this.message = message;
  }
}
