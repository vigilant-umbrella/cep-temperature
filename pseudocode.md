Algorithm 1
Input: data
rule <- model_training_and_rule_generation(data)
% Pass the rule to the CEP engine
cep <- CEP(rule)
Run cep indefinitely to get alerts

Algorithm 2: Model Training and Rule Generation
Input: Temperature Sensor Data
Output: CEP Rule
env_settings <- create a new pyflink environment
source <- create stream of data using pyflink
% Find the most accurate model for the data
for model <- {LR, Ridge, SVC} do
    Fit the model on source stream
    s <- model accuracy
    if s > best_score do
        best_score <- s
        best_model <- model
    endif
endfor
% Generate the rule using the best model
coef <- best_model.coef
intercept <- best_model.intercept
rule <- temp > -intercept / coef

Algorithm 3: Building CEP Engine
Input: rule
Output: CEP model
Create socket connection to receive live stream of data
outside_pattern <- Use Apache Flink Pattern API and rule to create outside pattern
inside_pattern <- Use Apache Flink Pattern API and rule to create inside pattern
outside_alert_event <- Use Apache Flink Pattern API and outside_pattern to create outside alert event
inside_alert_event <- Use Apache Flink Pattern API and inside_pattern to create inside alert event
build CEP engine using patterns and alert events to generate alerts on socket stream
