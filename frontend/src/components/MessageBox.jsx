import { Button, Form, Input } from "antd";
import { generateMessage } from "../api/messageApi";
import { useState } from "react";

const MessageBox = () => {
  const [form] = Form.useForm();
  const [message, setMessage] = useState("");
  const [topic, setTopic] = useState("");

  const handleClick = async () => {
    try {
      const response = await generateMessage(topic);
      setMessage(response?.data?.messageBody);
    } catch (error) {
      console.error("Error generating message:", error);
    }
  };

  return (
    <Form form={form} layout={"vertical"}>
      <Form.Item label="Topic" name="topic" rules={[{ required: true }]}>
        <Input
          type="text"
          value={topic}
          onChange={(e) => setTopic(e.target.value)}
        />
      </Form.Item>
      <Button type="primary" onClick={handleClick}>
        Generate
      </Button>
      <div>{message}</div>
    </Form>
  );
};

export default MessageBox;
