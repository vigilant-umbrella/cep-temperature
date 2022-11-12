from sklearn.metrics import precision_recall_fscore_support
from pyflink.table import TableEnvironment
from pyflink.table import EnvironmentSettings
from pyflink.table import DataTypes
from pyflink.table import CsvTableSource
from pyflink.table.types import DataTypes

from sklearn.svm import LinearSVC

import warnings
warnings.filterwarnings("ignore")

def main():
    env_settings = EnvironmentSettings.new_instance().in_batch_mode().build()
    tbl_env = TableEnvironment.create(env_settings)

    column_names = ['id','room_id','noted_date','temp','result']
    
    column_types = [DataTypes.STRING(),DataTypes.STRING(),DataTypes.STRING(),DataTypes.STRING(),DataTypes.STRING(),]

    
    source = CsvTableSource(
        './rule_generation/IOT-temp.csv',
        column_names,
        column_types,
        ignore_first_line=True
    )
    
    tbl_env.register_table_source('temp_recs', source)
    
    tbl = tbl_env.from_path('temp_recs')

    tbl = tbl.drop_columns(tbl.id, tbl.room_id, tbl.noted_date)


    X = tbl.select(tbl.temp).to_pandas()
    y = tbl.select(tbl.result).to_pandas()

    model = LinearSVC(random_state=42, class_weight='balanced', dual=False)

    model.fit(X, y)
    

    print(f'if {model.coef_[0][0]} * temp + {model.intercept_[0]} > 0 then {model.classes_[1]} else {model.classes_[0]}')
    print(f'Rule: if temp > {-model.intercept_[0] / model.coef_[0][0]} then {model.classes_[1]} else {model.classes_[0]}')


    s = model.score(X, y)
    print(f'Accuracy: {s}')

    print(f'Precision, recall, fscore, support = {precision_recall_fscore_support(y, model.predict(X), average="macro")}')
    
if __name__ == '__main__':
    main()
