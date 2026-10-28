# Job Application Tracker

Full-stack React + Spring Boot application with persistent file-based H2 database.

## Requirements
Java 17+, Maven 3.9+, Node.js 18+.

## Start backend
```bash
cd backend
mvn spring-boot:run
```
API: `http://localhost:8080`. H2 data persists in `backend/data/jobtracker`.

## Start frontend (second terminal)
```bash
cd frontend
npm install
npm run dev
```
Open `http://localhost:5173`.

## API
- `GET /api/applications` (optional `?status=Applied`)
- `GET /api/applications/count`
- `POST /api/applications`
- `PATCH /api/applications/{id}/status`
- `DELETE /api/applications/{id}`

POST example:
```json
{"companyName":"Acme","jobRole":"Software Engineer","applicationDate":"2026-10-27","status":"Applied"}
```
Statuses: `Applied`, `Interview`, `Selected`, `Rejected`.

## Validation / errors
Company name and job role are required; application date is required and cannot be in the future; status must be valid. Errors return a JSON `message`. Missing IDs return 404.

## Tests
```bash
cd backend
mvn test
```
