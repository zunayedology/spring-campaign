import { Button, Form, Input, Modal } from "antd";
import { useEffect } from "react";
import { createCustomer, updateCustomer } from "../api/customerApi";

const CustomerModal = ({ visible, onClose, onRefresh, customer }) => {
  const [form] = Form.useForm();
  useEffect(() => {
    if (customer) {
      form.setFieldsValue(customer);
    } else {
      form.resetFields();
    }
  }, [customer, form]);

  const handleSubmit = async () => {
    const values = await form.validateFields();
    if (customer) {
      await updateCustomer(customer.id, values);
    } else {
      await createCustomer(values);
    }
    onRefresh();
    onClose();
  };

  return (
    <Modal
      open={visible}
      title={customer ? "Update Customer" : "New Customer"}
      onCancel={onClose}
      footer={[
        <Button key="cancel" onClick={onClose}>
          Cancel
        </Button>,
        <Button key="submit" type="primary" onClick={handleSubmit}>
          {customer ? "Update Customer" : "New Customer"}
        </Button>,
      ]}
    >
      <Form form={form} layout="vertical">
        <Form.Item
          label="Customer Name"
          name="customerName"
          rules={[{ required: true }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          label="Phone Number"
          name="phoneNumber"
          rules={[{ required: true }]}
        >
          <Input minLength={11} maxLength={11} style={{ width: "auto" }} />
        </Form.Item>
      </Form>
    </Modal>
  );
};

export default CustomerModal;
