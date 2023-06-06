import React from "react";
import "./LChart.css";
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
  ResponsiveContainer,
} from "recharts";

const LChart = ({ title, data }) => {
  return (
    <div className="LChart">
      <h4 className="LChartTitle">{title}</h4>
      <div style={{ padding: "20px" }}>
        <ResponsiveContainer aspect={11 / 6}>
          {/* 가로 세로 비율 조정 */}
          <LineChart data={data} margin={{ left: -30, right: 35, bottom: -15 }}>
            <CartesianGrid stroke="silver" strokeDasharray="2 2" />{" "}
            {/* 배경에 그리드 선 */}
            <XAxis
              dataKey="semester"
              label={{ value: "학기", offset: 10, position: "right" }}
            />
            <YAxis tickCount={10} padding={{ top: 10 }} />
            <Tooltip />
            <Legend
                align="right"
                payload={[
                    { value: '나', type: 'line', color: '#007FFF' },
                  { value: '평균', type: 'line', color: 'darkgray' }
                ]}
            />
            <Line
              type="monotone"
              dataKey="myData"
              stroke="#007FFF"
              activeDot={{ r: 8 }}
              name="나"
            />
            <Line type="monotone" dataKey="avgData" stroke="darkgray" name="평균"/>
          </LineChart>
        </ResponsiveContainer>
      </div>
    </div>
  );
};

export default LChart;
