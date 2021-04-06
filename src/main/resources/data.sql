CREATE TABLE IF NOT EXISTS templates (
    template_id VARCHAR PRIMARY KEY,
    template VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS templates_recipients (
    template_id VARCHAR,
    recipient_id VARCHAR,
    PRIMARY KEY (template_id, recipient_id)
);

CREATE TABLE IF NOT EXISTS templates_variables (
  template_id VARCHAR,
  variable VARCHAR,
  variable_value VARCHAR,
  PRIMARY KEY (template_id, variable)
);