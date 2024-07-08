import axios from "axios";

const API_URL = "http://localhost:8080/api";

const axiosInstance = axios.create({
  baseURL: API_URL,
  timeout: 100000,
  headers: {
    "Content-Type": "application/json",
  },
});

export const getAllMessages = () => axiosInstance.get("/messages");
export const getMessage = (id) => axiosInstance.get(`/messages/${id}`);
export const generateMessage = (topic) =>
  axiosInstance.post(`/messages/ai?topic=${topic}`);
export const updateMessage = (id, message) =>
  axiosInstance.put(`/messages/${id}`, message);
export const deleteMessage = (id) => axiosInstance.delete(`/messages/${id}`);
